# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

import mysql.connector

# MySQL pipeline configuration
host = "localhost"
user = "root"
passwd = "Logan@123"
dbname = "eatigo_op_data"


class RoblerPipeline(object):
    def __init__(self):
        self.cnx = mysql.connector.connect(user=user,
                                           password=passwd,
                                           host=host,
                                           database=dbname,
                                           charset="utf8",
                                           use_unicode=True)
        self.cursor = self.cnx.cursor()

    def process_item(self, item, spider):
        try:
            id = item['unique_id']
            name = item['name'].encode('utf-8')
            url = item['url'].encode('utf-8')
            img_url = item['img_url'].encode('utf-8')
            category = item['category'].encode('utf-8')
            location = item['location'].encode('utf-8')
            booked_counts = item['booked_counts']
            discount_percentage = item['discount_percentage'].encode('utf-8')
            item_count = item['page_item_count']
            page_count = item['page_counter']

            zero_book_flag = 1
            if booked_counts > 0:
                zero_book_flag = 0

            query = """INSERT INTO offpeak_restaurants (id, name, url, img_url, category
            , location, booked_counts, discount_percentage, item_count, page_count, zeroflag)
            VALUES ({0}, '{1}', '{2}', '{3}', '{4}', '{5}', {6}, '{7}', {8}, {9}, {10})
            ON DUPLICATE KEY UPDATE name = '{1}', url = '{2}', img_url = '{3}', category = '{4}', location = '{5}'
            , booked_counts = {6}, discount_percentage = '{7}', item_count = {8}, page_count = {9}"""\
                .format(id, name, url, img_url, category, location
                        , booked_counts, discount_percentage, item_count, page_count, zero_book_flag)
            self.cursor.execute(query)

            self.cnx.commit()
        except mysql.connector.Error, e:
            print "Error %d: %s" % (e.args[0], e.args[1])
        return item
