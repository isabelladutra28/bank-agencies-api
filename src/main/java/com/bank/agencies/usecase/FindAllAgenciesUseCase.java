package com.bank.agencies.usecase;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.external.gateway.AgenciesGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FindAllAgenciesUseCase {

    private final AgenciesGateway bankResourcesGateway;

    public FindAllAgenciesUseCase(AgenciesGateway bankResourcesGateway) {
        this.bankResourcesGateway = bankResourcesGateway;
    }

    public List<AgencyGatewayResponse> execute() {
        return bankResourcesGateway.findAllAgencies();
    }
    
    public List<AgencyGatewayResponse> executeFilterState(String state) {
        return bankResourcesGateway.findAgenciesByState(state);
    }
    
    public List<AgencyGatewayResponse> executeGroupingByState() {
        return bankResourcesGateway.findAllAgencies();
    }

	

	
}
