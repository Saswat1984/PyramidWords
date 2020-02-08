package org.example.handler;

import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class PyramidImpl implements Pyramid {
    int HTTPS_SUCCESS = 200;
    int HTTPS_FAILURE = 400;
    @Override
    public void handle(RoutingContext rc) {

        String word = rc.request().getParam("word");
        System.out.println("Request received with body : " + word);
        boolean isPyramidWord = StringUtils.isNotBlank(word) ? checkIfWordIsPyramidWord(word) : false;
        StringBuilder statusMessage = new StringBuilder();
        statusMessage.append("The word : ")
                .append(word);
        if (isPyramidWord){
            rc.response()
                    .setStatusCode(HTTPS_SUCCESS);
            statusMessage.append(", is a pyramid word.");
        } else {
            rc.response()
                    .setStatusCode(HTTPS_FAILURE);
            statusMessage.append(", is not a pyramid word.");
        }
        rc.response()
                .setChunked(true)
                .end(String.valueOf(statusMessage.toString()));
    }

    public boolean checkIfWordIsPyramidWord(String word){
        Map<String, Integer> stringValueMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++){
            String s = word.substring(i, i+1);
            if (stringValueMap.containsKey(s)){
                stringValueMap.put(s, stringValueMap.get(s)+1);
            } else {
                stringValueMap.put(s, 1);
            }
        }

        List<Integer> valueSet = new ArrayList<>();
        stringValueMap.forEach((s, v) -> {
            valueSet.add(v);
        });
        Collections.sort(valueSet);
        int val = 0;
        for (int i : valueSet) {
            if (val == 0){
                val = i;
            } else if (i == val+1){
                val = i;
            } else {
                return false;
            }
        }
        return true;
    }
}
