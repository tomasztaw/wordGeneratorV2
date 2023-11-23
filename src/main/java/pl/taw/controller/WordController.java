/**
 * Created by tomasz_taw
 * Date: 23.11.2023
 * Time: 19:46
 * Project Name: wordGeneratorV2
 * Description:
 */
package pl.taw.controller;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/word")
public class WordController {

    private final AiClient aiClient;

    @Value("${spring.ai.openai.api-key}")
    String openaiApiKey;

    public WordController(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    @GetMapping("/fullDesc")
    public String fullDescriptionWord(@RequestParam(name = "word") String word) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Proszę opisać słowo {word} pod kątem jego znaczenia i zastosowania.
                Czy mogę dowiedzieć się, skąd wywodzi się słowo {word}? Jakie jest jego etymologiczne pochodzenie?
                Czy istnieją synonimy i antonimy dla słowa {word}? Proszę podać kilka przykładów.
                Jakie są typowe sytuacje lub konteksty, w których pojęcie {word} jest często używane?
                Czy można przedstawić historię i ewolucję tego słowa w kontekście kultury lub wydarzeń historycznych?
                Czy można podać kilka przykładów zdań, w których słowo {word} jest używane w praktyce?
                Czy to słowo ma negatywne lub pozytywne konotacje w różnych kontekstach społecznych? Jakie są emocjonalne reakcje związane z tym słowem?
                Czy jest coś jeszcze, co warto wiedzieć na temat słowa {word}?
                Odpowiedź ma być w formacie JSON: opis, pochodzenie, synonimy, antonimy, kontekst, znaczenie, przykłady, ciekawostki.
                Jeżeli nie jesteś pewien czy znaczenie jest poprawne, to proszę nie wymyślaj, tylko daj znać, że nie jesteś pewien.
                """);
        promptTemplate.add("word", word);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }

}
