# -*- coding: utf-8 -*-
import time
import scrapy

# MySQL, position & class constants
spider_url = 'http://offpeak.my/restaurants/category/latest-offers'
next_spider_url = spider_url + '?page='
unique_id_pos = 4
class_discount = "disc"
class_image = "img"
class_info = "info"
class_title = "name"
class_resto = "restaurant-name"
class_booked_count = "no-booked"
condition_check = 1494849599


class MyOffPeakSpider(scrapy.Spider):
    name = "my-offpeak"
    start_urls = [spider_url]
    page_counter = 67

    def parse(self, response):
        item_selector = ".p-item"
        next_page_flag = False
        self.page_counter += 1
        for idx, element in enumerate(response.css(item_selector)):
            page_item_count = idx + 1
            # x-path logics
            url_selector = ".//*[@id='restaurant-listing']/div[" + str(page_item_count) + "]/div/div/a/@href"
            discount_selector = "//*[@id='restaurant-listing']/div[" + str(page_item_count) \
                                + "]/div/div/a/div[contains(@class,'" \
                                + class_discount + "')]/text()"
            img_selector = "//*[@id='restaurant-listing']/div[" + str(page_item_count) \
                           + "]/div/div/a/div[contains(@class,'" + class_image + "')]/img/@src"
            title_selector = "//*[@id='restaurant-listing']/div[" + str(page_item_count) \
                             + "]/div/div/a/div[contains(@class,'" \
                             + class_info + "')]/div[contains(@class,'" + class_title + "')]/text()"
            cat_loc_selector = "//*[@id='restaurant-listing']/div[" + str(page_item_count) \
                               + "]/div/div/a/div[contains(@class,'" \
                               + class_info + "')]/div[contains(@class,'" + class_resto + "')]/text()"
            booking_selector = "//*[@id='restaurant-listing']/div[" + str(page_item_count) \
                               + "]/div/div/a/div[contains(@class,'" \
                               + class_info + "')]/div[contains(@class,'" + class_booked_count + "')]/text()"

            url = response.xpath(url_selector).extract_first()
            unique_id = url.split('/')[unique_id_pos]
            discount_percentage = response.xpath(discount_selector).extract_first()
            img_url = response.xpath(img_selector).extract_first()
            name = response.xpath(title_selector).extract_first().strip()
            category = response.xpath(cat_loc_selector).extract_first().strip().split('/')[0].strip()
            location = response.xpath(cat_loc_selector).extract_first().strip().split('/')[1].strip()
            booked_counts = int(response.xpath(booking_selector).extract_first().strip().split(' ')[0].strip())
            next_page_flag = True
            yield {
                'page_item_count': page_item_count,
                'page_counter': self.page_counter,
                'url': url,
                'discount_percentage': discount_percentage,
                'img_url': img_url,
                'name': name,
                'category': category,
                'location': location,
                'booked_counts': booked_counts,
                'unique_id': unique_id
            }

        if next_page_flag and time.time() <= condition_check:
            next_page_url = next_spider_url + str(self.page_counter)
            yield scrapy.Request(
                response.urljoin(next_page_url)
                , callback=self.parse
            )
