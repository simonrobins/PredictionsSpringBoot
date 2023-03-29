package uk.simonrobins.PredictionsSpringBoot.controller.ws;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private PredictionService predictionService;
    private FixtureService fixtureService;

    public WebSocketHandler(PredictionService predictionService, FixtureService fixtureService) {
        this.predictionService = predictionService;
        this.fixtureService = fixtureService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try {
            String[] fragments = message.getPayload().split(":");
            switch (fragments[0]) {
                case "prediction":
                    Result result = Result.valueOf(fragments[1]);
                    Long predictionId = Long.parseLong(fragments[2]);
                    predictionService.updateResult(predictionId, result);
                    break;
                case "home":
                    // Need to check for a completely empty string here and set home goals back to null
                    Integer homeGoals = Integer.parseInt(fragments[1]);
                    Long homeId = Long.parseLong(fragments[2]);
                    fixtureService.updateHomeGoals(homeId, homeGoals);
                    break;
                case "away":
                    // Need to check for a completely empty string here and set away goals back to null
                    Integer awayGoals = Integer.parseInt(fragments[1]);
                    Long awayId = Long.parseLong(fragments[2]);
                    fixtureService.updateAwayGoals(awayId, awayGoals);
                    break;
            }

            TextMessage ok = new TextMessage("OK");
            session.sendMessage(ok);
        } catch (Exception ex) {
            TextMessage fail = new TextMessage("FAIL");
            session.sendMessage(fail);
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        System.out.println("New Binary Message Received");
        session.sendMessage(message);
    }
}