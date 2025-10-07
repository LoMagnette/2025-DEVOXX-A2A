package be.lomagnette.bsu;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class BruceBaaner {

    private final StoneExtractor stoneExtractor;

    public BruceBaaner(StoneExtractor stoneExtractor) {
        this.stoneExtractor = stoneExtractor;
    }

    public String snap(String stonesString) throws Exception {
        var stones = this.stoneExtractor.collectAllStonePresentInAMessage(stonesString);
        var split = List.of(stones.substring(1, stones.length() - 1)
                .replaceFirst("\"","")
                .replaceAll("\"","")
                .split(","))
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());

        if(split.size() == 6){
            return "Bruce Baaaner snaped and restored the universe thanks to "+ String.join(", ", split);

        }
        throw new Exception("Cannot restored the universe");
    }


}
