package com.bank.kantor.kantor;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Status {
    private int value;
    private int message;

    public Integer getStatusIdKantorNotFound(){
        return 431;
    }

    public String getMessageIdKantorNotFound(){
        return "Id Kantor Tidak Ditemukan!";
    }

    public Integer getStatusIdKantorFound(){
        return 230;
    }

    public String getMessageIdKantorFound(){
        return "Id Kantor Ditemukan!";
    }
}
