package com.cathay.holdings.myr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstTryDTO implements Serializable {
    private static final long serialVersionUID = -1731135548978108444L;

    String desc;
}
