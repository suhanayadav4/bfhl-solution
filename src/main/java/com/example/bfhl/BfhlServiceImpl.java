package com.example.bfhl;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDTO processData(RequestDTO request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            // Check if number
            try {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } catch (NumberFormatException e) {
                // Not a number
                if (item.length() == 1) {
                    char c = item.charAt(0);
                    if (Character.isLetter(c)) {
                        alphabets.add(String.valueOf(Character.toUpperCase(c)));
                    } else {
                        specialCharacters.add(item);
                    }
                } else {
                    // Multi character string - check each char
                    boolean allAlpha = true;
                    for (char c : item.toCharArray()) {
                        if (!Character.isLetter(c)) {
                            allAlpha = false;
                            break;
                        }
                    }
                    if (allAlpha) {
                        alphabets.add(item.toUpperCase());
                    } else {
                        specialCharacters.add(item);
                    }
                }
            }
        }

        // Concat string - reverse alphabets in alternating caps
        List<String> reversed = new ArrayList<>(alphabets);
        Collections.reverse(reversed);
        StringBuilder concat = new StringBuilder();
        int capIndex = 0;
        for (String alpha : reversed) {
            for (char c : alpha.toCharArray()) {
                if (capIndex % 2 == 0) {
                    concat.append(Character.toUpperCase(c));
                } else {
                    concat.append(Character.toLowerCase(c));
                }
                capIndex++;
            }
        }

        ResponseDTO response = new ResponseDTO();
        response.setIs_success(true);
        response.setUser_id("suhana_yadav_04032006");
        response.setEmail("suhanayadav230760@acropolis.in");
        response.setRoll_number("0827CI231133");
        response.setOdd_numbers(oddNumbers);
        response.setEven_numbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecial_characters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(concat.toString());

        return response;
    }
}
