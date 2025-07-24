package Esport_Website.service;

import Esport_Website.DAO.NewsDAO;
import Esport_Website.entity.News;
import Esport_Website.entity.NewsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LatestNewsService {
    @Autowired
    private NewsDAO newsDAO;

    public String getLatestNewsContent() {
        News latestNews = newsDAO.findLatestNewsWithDetails();
        if (latestNews != null) {
            StringBuilder content = new StringBuilder();
            content.append("Tiêu đề: ").append(latestNews.getTitle()).append("\n");
            content.append("Tóm tắt: ").append(latestNews.getSubtitle()).append("\n");
            if (latestNews.getNewsDetails() != null && !latestNews.getNewsDetails().isEmpty()) {
                NewsDetail detail = latestNews.getNewsDetails().get(0);
                content.append("Nội dung: ").append(detail.getDetail());
            }
            return content.toString();
        }
        return "Hiện chưa có tin tức mới.";
    }

    public String getTop3NewsHtmlContent() {
        Pageable pageable = PageRequest.of(0, 3);
        List<News> newsList = newsDAO.findTop3NewsWithDetails(pageable);
        StringBuilder html = new StringBuilder();
        html.append("<h2 style='font-family:sans-serif;'>Bản tin Esport mới nhất</h2>");
        for (int i = 0; i < newsList.size(); i++) {
            News news = newsList.get(i);
            html.append("<div style='background:#fff; border-radius:12px; box-shadow:0 2px 8px rgba(0,0,0,0.08); padding:20px; border:1px solid #e0e0e0; width:100%; display:block;'>");
            html.append("<a href='https://esport-website.onrender.com/detail/")
                .append(news.getNewsId())
                .append("' style='text-decoration:none; color:#2e6da4;'><h3 style='margin:0;'>")
                .append(news.getTitle())
                .append("</h3></a>");
            html.append("<div style='color:#555; font-size:16px; margin-top:8px;'>")
                .append(news.getSubtitle())
                .append("</div>");
            html.append("</div>");
            if (i < newsList.size() - 1) {
                html.append("<br><hr style='border:1px solid #eee;'><br>");
            }
        }
        return html.toString();
    }
} 