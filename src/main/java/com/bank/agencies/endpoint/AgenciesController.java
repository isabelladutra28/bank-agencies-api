    package com.bank.agencies.endpoint;

    import com.bank.agencies.domain.AgencyGatewayResponse;
    import com.bank.agencies.domain.AgencyResponse;
    import com.bank.agencies.usecase.FindAllAgenciesUseCase;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

    @RestController
    @RequestMapping(value = "/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public class AgenciesController {

        private final FindAllAgenciesUseCase findAllAgenciesUseCase;

        public AgenciesController(FindAllAgenciesUseCase findAllAgenciesUseCase) {
            this.findAllAgenciesUseCase = findAllAgenciesUseCase;
        }

        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<AgencyResponse>> findAllAgencies() {

            List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.execute();

            List<AgencyResponse> agencyResponse = agencies.stream()
                    .map(agencyGateway -> AgencyResponse.AgencyResponseBuilder.anAgencyResponse()
                    .bank(agencyGateway.getBank())
                    .city(agencyGateway.getCity())
                    .name(agencyGateway.getName())
                    .state(agencyGateway.getState()).build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(agencyResponse, HttpStatus.OK);
        }
        
        @GetMapping("{state}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<AgencyResponse>> findAgenciesByState(@PathVariable String state) {

            List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.executeFilterState(state);
         
            
            List<AgencyResponse> agencyResponse = agencies.stream()
                    .map(agencyGateway -> AgencyResponse.AgencyResponseBuilder.anAgencyResponse()
                    .bank(agencyGateway.getBank())
                    .city(agencyGateway.getCity())
                    .name(agencyGateway.getName())
                    .state(agencyGateway.getState()).build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(agencyResponse, HttpStatus.OK);
        }
        
  @GetMapping("/group")     
  @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Map<String, List<AgencyResponse>>> GroupAgenciesByState() {

             List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.executeGroupingByState();
        
            
            List<AgencyResponse> agencyResponses =  agencies.stream()
                    .map(agencyGateway -> AgencyResponse.AgencyResponseBuilder.anAgencyResponse()
                    .bank(agencyGateway.getBank())
                    .city(agencyGateway.getCity())
                    .name(agencyGateway.getName())
                    .state(agencyGateway.getState()).build())
                    .collect(Collectors.toList());
            Collections.sort(agencyResponses, Comparator.comparing(AgencyResponse::getCity));           
            Map<String, List<AgencyResponse>> agencyResponsedd = agencyResponses.stream().collect(Collectors.groupingBy(AgencyResponse:: getState));

            

            return new  ResponseEntity<>(agencyResponsedd, HttpStatus.OK);
        }
    }
