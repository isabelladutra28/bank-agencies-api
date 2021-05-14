package com.bank.agencies.external.gateway;

import com.bank.agencies.domain.AgencyGatewayResponse;

import java.util.List;
import java.util.Map;

public interface AgenciesGateway {
    List<AgencyGatewayResponse> findAllAgencies();
    
    List<AgencyGatewayResponse> findAgenciesByState(String state);
}
