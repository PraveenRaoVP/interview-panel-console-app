package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Candidate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateRepository {
    private static CandidateRepository instance;

    private CandidateRepository() {
    }

    public static CandidateRepository getInstance() {
        if (instance == null) {
            instance = new CandidateRepository();
        }
        return instance;
    }

    private Map<Integer, Candidate> candidateMap = new HashMap<>();

    public Candidate getCandidateById(int candidateId) {
        return candidateMap.get(candidateId);
    }

    public int getCandidatesSize() {
        return candidateMap.size();
    }

    public void pushCandidate(Candidate candidate) {
        candidateMap.put(candidate.getCandidateId(), candidate);
    }

    public Candidate getCandidateByID(int candidateId) {
        return candidateMap.get(candidateId);
    }

    public void removeCandidateByID(int candidateId) {
        candidateMap.remove(candidateId);
    }

    public List<Candidate> searchCandidateByName(String name) {
        List<Candidate> searchResults = new ArrayList<>();
        for(Candidate candidate: candidateMap.values()) {
            if(candidate.getName().toLowerCase().contains(name.toLowerCase()) || candidate.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(candidate);
            }
        }
        return searchResults;
    }

    public List<Candidate> getCandidates() {
        return candidateMap.values().stream().toList();
    }

    private String fileNamePath = "./src/main/resources/candidates.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, candidateMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                candidateMap.clear();
                candidateMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Candidate>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintointerviewpanels.json does not exist.");
        }
    }
}
