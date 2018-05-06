package com.bank.vo;

import java.math.BigDecimal;
import java.util.Optional;

public class AccounStatisticsVO {

    private Optional<BigDecimal> soma;

    private Optional<BigDecimal>  min;

    private Optional<BigDecimal>  max;

    private Optional<BigDecimal>  media;

    private Long  qtde;

    public AccounStatisticsVO(Optional<BigDecimal> soma, Optional<BigDecimal> min, Optional<BigDecimal> max, Optional<BigDecimal> media, Long qtde) {
        this.soma = soma;
        this.min = min;
        this.max = max;
        this.media = media;
        this.qtde = qtde;
    }

    public Optional<BigDecimal> getSoma() {
        return soma;
    }

    public void setSoma(Optional<BigDecimal> soma) {
        this.soma = soma;
    }

    public Optional<BigDecimal> getMin() {
        return min;
    }

    public void setMin(Optional<BigDecimal> min) {
        this.min = min;
    }

    public Optional<BigDecimal> getMax() {
        return max;
    }

    public void setMax(Optional<BigDecimal> max) {
        this.max = max;
    }

    public Optional<BigDecimal> getMedia() {
        return media;
    }

    public void setMedia(Optional<BigDecimal> media) {
        this.media = media;
    }

    public Long getQtde() {
        return qtde;
    }

    public void setQtde(Long qtde) {
        this.qtde = qtde;
    }
}
