package main;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

import main.domain.Suspect;
import main.service.FileService;

public class SuspectFinder {

	public static void main(String[] args) {
		FileService fileService = new FileService();
		List<Suspect> suspectLineUp = fileService.getSuspectsFromFile();
				
		// There should only be one person that is returned here...
		// The map listed here is the birth year (Integer) and the List of suspects
		// first filter out all the suspects with red hair...
		// then filter out all the suspects with glasses
		// then group by their birth year (Collectors.groupingBy(Suspect::getBirthYear, Collectors.toList())
		//   -> this will return the birth year and a list of suspect objects (if done correctly, there should
		//		only be one suspect object.
		
//		1. Using streams, get the list of 5 suspects and find out who is guilty!
//		2. filter out suspects with red hair
//		3. Filter out suspects with glasses
//		4. Group by birth year
//		5. Print out the name only
		Map<Integer, List<Suspect>> suspects = //
				suspectLineUp.stream()
							 .filter(suspect -> !suspect.getHairColor().equals("RED"))
							 .filter(suspect -> !suspect.getHasGlasses().equals(true))
							 .collect(Collectors.groupingBy(Suspect::getBirthYear, Collectors.toList()));
		
		// APPLY FILTERING LOGIC HERE....
		// I put it up there, is that wrong?
		String guilty = suspects.entrySet()
								.stream()
								.map(entry -> entry.getValue()
												   .get(0)
												   .getName())
								.findFirst()
								.orElse(null);

		System.out.println(guilty);	
	}
}
