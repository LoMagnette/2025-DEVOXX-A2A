package be.lomagnette;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@RegisterAiService
@SystemMessage("""
            You're IronRam, genius, billionaire, philantropist. You're a super hero to protect universe 8444.
            You can fly through space using your IronRamArmor navigation capability.
            You can also collect object through the universe.
            """)
@ApplicationScoped
public interface IronRam {


    @UserMessage("""
            You need to collect all objects matching the keywords.
            Follow these steps carefully and use the available tools in the correct order.
            	1.	Search Phase:
            	    -	Use the listing tool to find all objects that match the given keywords or objectDescription.
            	    -	Store their names and any location identifiers.
            	2.	Navigation & Collection Phase:
            	    -	For each object found:
                        a. Use the navigation tool to go to the object’s location.
                        b. Once there, use the collection tool to collect the object.
                        c. Keep track of the object name after successful collection.
            	3.	Return Phase:
            	    - After all objects have been collected, return a list containing only the names of the collected objects.
            	    - The final response must be a structured list (e.g. an array of strings).
            	    - Correct answers:
                        - ["The Time Fleece Gem", "The Space Fleece Gem", "The Reality Fleece Gem", "The Power Fleece Gem", "The Mind Fleece Gem", "The Soul Fleece Gem"]
                    - Incorrect answers:
                        - ["[\\"The Time Fleece Gem\\", \\"The Space Fleece Gem\\", \\"The Reality Fleece Gem\\", \\"The Power Fleece Gem\\", \\"The Mind Fleece Gem\\", \\"The Soul Fleece Gem\\"]"]
            	        - VALID ANSWER: ["The Time Fleece Gem", "The Space Fleece Gem", "The Reality Fleece Gem", "The Power Fleece Gem", "The Mind Fleece Gem", "The Soul Fleece Gem"]
            	        - INVALID ANSWER: ["[\\"The Time Fleece Gem\\", \\"The Space Fleece Gem\\", \\"The Reality Fleece Gem\\", \\"The Power Fleece Gem\\", \\"The Mind Fleece Gem\\", \\"The Soul Fleece Gem\\"]"]
        
            Rules:
            	-	Always use the correct tool for each step: list → navigate → collect.
            	-	Process one object at a time.
            	-	Do not skip navigation before collection.
            	-	The final output must only include the names of the collected objects, nothing else.
        
            The answer should be a simple json array of string without any specials characters
        
        ---
           keywords: {objectsDescription}
        
        """)
    @ToolBox({Baarvis.class, IronRamArmor.class})
    public List<String> collect(String objectsDescription);
}
