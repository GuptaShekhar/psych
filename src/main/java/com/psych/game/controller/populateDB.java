package com.psych.game.controller;

import com.psych.game.Constants;
import com.psych.game.Utils;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.repository.PlayerRepository;
import com.psych.game.repository.QuestionRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/dev")
public class populateDB {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/add-questions-from-files")
    public void addQuestionsFromFiles() throws IOException {
        questionRepository.deleteAll();
        for(Map.Entry<String, GameMode> entry: Constants.QA_FILES.entrySet()){
            int questionNumber = 0;
            String fileName = entry.getKey();
            GameMode gameMode = entry.getValue();
            for(Pair<String, String> question_answer: Utils.readQAFile(fileName)){
                Question q = new Question();
                q.setQuestionText(question_answer.getKey());
                q.setCorrectAnswer(question_answer.getValue());
                q.setGameMode(gameMode);
                questionRepository.save(q);
                questionNumber++;
                if(questionNumber > Constants.MAX_QUESTIONS_TO_READ)
                    break;
            }
        }

    }


    @GetMapping("/add-dummy-players")
    public void addDummyPlayers() throws IOException {
        playerRepository.deleteAll();

        Player luffy = new Player();
        luffy.setName("Monkey D. Luffy");
        luffy.setPicURL("https://i.imgur.com/PrCEBd7.png");
        luffy.setPsychFaceURL("https://i.imgur.com/SPzynwl.png");
        Player robin = new Player();
        robin.setName("Nico Robin");
        robin.setPicURL("https://i.imgur.com/kB7StJm.png");
        robin.setPsychFaceURL("https://i.imgur.com/tnJTeaG.png");

        playerRepository.save(luffy);
        playerRepository.save(robin);
    }

}
