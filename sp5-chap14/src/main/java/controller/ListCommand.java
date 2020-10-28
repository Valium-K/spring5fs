package controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

// selectByRegdate를 위한 커맨드
public class ListCommand {

    // String -> LocalDateTime 변환
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime to;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
