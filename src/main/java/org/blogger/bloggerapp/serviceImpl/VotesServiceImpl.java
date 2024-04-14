package org.blogger.bloggerapp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Votes;
import org.blogger.bloggerapp.repository.IVotesRepository;
import org.blogger.bloggerapp.service.IVotesService;
import org.blogger.bloggerapp.utility.AppUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotesServiceImpl implements IVotesService {
    private final IVotesRepository votesRepo;
    private final AppUtils appUtils;

    @Override
    public Long upVote(Long blogId) {
        return castVote(blogId, 1L, 0L);
    }

    private Long castVote(Long blogId, Long upvote, Long downVote) {
        BlogPosts blog = appUtils.getBlog(blogId);
        Votes votes = new Votes();
        votes.setUpVote(upvote);
        votes.setDownVote(downVote);
        votes.setPost(blog);
        votes.setUser(appUtils.currentLoginUser());
        votesRepo.save(votes);
        return upvote == 1L ? votesRepo.upVoteCountByPost(blogId) : votesRepo.downVoteCountByPost(blogId);
    }

    @Override
    public Long downVote(Long blogId) {
        return castVote(blogId, 0L, 1L);
    }
}