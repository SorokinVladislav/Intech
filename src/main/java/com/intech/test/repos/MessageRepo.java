package com.intech.test.repos;

import com.intech.test.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
