package com.icegone.spring.boot.blog.service.impl;

import javax.transaction.Transactional;

import com.icegone.spring.boot.blog.repository.CommentRepository;
import com.icegone.spring.boot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icegone.spring.boot.blog.domain.Comment;

/**
 * Comment 服务.
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	/* (non-Javadoc)
	 * @see CommentService#removeComment(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.delete(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findOne(id);
	}

}
