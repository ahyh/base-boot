package com.yh.boot.elasticsearch.demo.esdao;

import com.yh.boot.elasticsearch.demo.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * User-ElasticSearch-Dao
 *
 * @author yanhuan
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
