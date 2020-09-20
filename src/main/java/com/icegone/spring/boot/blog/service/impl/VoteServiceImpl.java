package com.icegone.spring.boot.blog.service.impl;

import javax.transaction.Transactional;

import com.icegone.spring.boot.blog.repository.VoteRepository;
import com.icegone.spring.boot.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icegone.spring.boot.blog.domain.Vote;

/**
 * Vote 服务.
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.delete(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findOne(id);
	}

}
