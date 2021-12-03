package com.github.prgrms.orders;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long seq;
    private Long userSeq;
    private Long productSeq;
    private Long reviewSeq;
    private State state;
    private String requestMsg;
    private String rejectMsg;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public enum State {
        REQUESTED,ACCEPTED,SHIPPING,COMPLETED, REJECTED;
    }

    @Builder
    public Order(Long seq, Long userSeq, Long productSeq, Long reviewSeq, State state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    public void accept() throws IllegalStateException{
        if(this.state != State.REQUESTED) throw new IllegalStateException();
        this.state = State.ACCEPTED;
    }

    public void reject() throws IllegalStateException{
        if(this.state != State.REQUESTED) throw new IllegalStateException();
        this.state = State.REJECTED;
        this.rejectedAt = LocalDateTime.now();
    }

    public void complete() throws IllegalStateException{
        if(this.state != State.SHIPPING) throw new IllegalStateException();
        this.state = State.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void shipping() throws IllegalStateException{
        if(this.state != State.ACCEPTED) throw new IllegalStateException();
        this.state = State.SHIPPING;
        this.completedAt = LocalDateTime.now();
    }

}
