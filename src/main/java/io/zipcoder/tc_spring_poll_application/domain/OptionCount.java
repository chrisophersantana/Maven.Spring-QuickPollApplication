package io.zipcoder.tc_spring_poll_application.domain;

public class OptionCount {


    private Long optionId;
    private int count;

    public OptionCount(Long id, Integer count) {
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
