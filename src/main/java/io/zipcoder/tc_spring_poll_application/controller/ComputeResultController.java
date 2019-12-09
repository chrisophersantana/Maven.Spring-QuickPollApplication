package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.*;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class ComputeResultController {

    private VoteRepository voteRepository;
@Autowired
   private PollRepository pollRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeResult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        Collection<OptionCount> optionCounts = new ArrayList<>();


        for(Option o : pollRepository.findOne(pollId).getOptions()){
            Integer count = 0;
            for(Vote v : allVotes) {
                if(v.getOption().equals(o)){
                    count++;
                }
            }
            OptionCount optionCount = new OptionCount(o.getId(),count);
            optionCounts.add(optionCount);
        }
        Integer totalVotes = 0;
        for(Vote v : allVotes){
            totalVotes++;
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(optionCounts);

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK); }

}
