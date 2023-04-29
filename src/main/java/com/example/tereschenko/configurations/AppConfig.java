package com.example.tereschenko.configurations;

import com.example.tereschenko.dao.AlbumDAO;
import com.example.tereschenko.dao.ArtistDAO;
import com.example.tereschenko.dao.TrackDAO;
import com.example.tereschenko.dao.impl.AlbumDAOImpl;
import com.example.tereschenko.dao.impl.ArtistDAOImpl;
import com.example.tereschenko.dao.impl.TrackDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AlbumDAO albumDAO() {
        return new AlbumDAOImpl();
    }

    @Bean
    public TrackDAO trackDAO(){return new TrackDAOImpl();}

    @Bean
    public ArtistDAO artistDAO(){return new ArtistDAOImpl();}
}
