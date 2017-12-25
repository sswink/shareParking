package com.front.Service;

import com.front.dto.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lin on 2017/9/29.
 */
public interface ParkingLotService {

    Result getParkinglotByPkId(long parkingId);

    Result getAllParkinglot ();

}
