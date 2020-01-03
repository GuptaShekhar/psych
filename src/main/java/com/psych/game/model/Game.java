package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="games")
public class Game extends Auditable{
    @Getter
    @Setter
    @NotNull
    private int numRounds;

    @Getter
    @Setter
    private int currentRound = 0;

    @ManyToMany
    @Getter
    @Setter
    private Map<Player, Stats> playerStats;
    //game_players_stats
    // game_id, player_id, stat_id   pk(game_id, player_id)

    @ManyToMany
    @Getter
    @Setter
    private List<Player> players;

    @Getter
    @Setter
    @NotNull
    private GameMode gameMode;

    @Getter
    @Setter
    private GameStatus gameStatus = GameStatus.JOINING;

    @ManyToOne
    @NotNull
    @Getter
    @Setter
    private Player leader;

    @OneToMany(mappedBy = "game")
    @Getter
    @Setter
    private List<Round> rounds;
}
