package com.example.demo.service;

import com.example.demo.entity.Book;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class BookRepoPageProcessor implements PageProcessor {


    @Override
    public void process(Page page) {

            List<Selectable> selectableList = new ArrayList<>();
            List<Book> books = new ArrayList<>();
            selectableList = page.getHtml().css("div.detail-frame").nodes();
            selectableList.forEach(it -> {
                Book book = new Book();
                book.setBookName(it.css("h2 a","text").get());
                book.setRating(it.css("p.rating span.font-small","text").get());
                book.setNote(it.css("p:last-child","text").get());
                book.setInfo(it.css("p.color-gray","text").get());
                books.add(book);
            });
            page.putField("books", books);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }
}
