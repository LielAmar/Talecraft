package dev.lielamar.talecraft.constants;

import java.util.UUID;

public class Constants {
	
	public static final int MAX_PLAYERS = 90;
	
	public static UUID[] youtubers = new UUID[] {
			UUID.fromString("a3fed1a7-06ba-4243-986f-ecc7b0cdc1f8"),   // LielAmar
			UUID.fromString("8850b4ea-532d-4a5b-8b94-edc4bbab4447"),   // PartizaNYT
			UUID.fromString("a5bc7f34-7f53-449a-9c4f-a027b91403bf"),   // Oogiya
			UUID.fromString("ba6047c6-db21-4095-bc9f-9bd40f2afd39"),   // Rozmarin
//			UUID.fromString("45c47dd2-cd31-4f20-bf0c-230a6933bb66"),   // iWinter
			UUID.fromString("2cdf30eb-b87b-4b24-a3df-e3dc3d60f2c9"),   // The ShinyBunny
			UUID.fromString("95b8aca3-e81f-47b4-824b-b9d82741bd4f"),   // Command.IL
			UUID.fromString("6af4fdea-11f9-465a-963d-58d19045a2a9"),   // Yoavnio / SnipTiger12
//			UUID.fromString("29f030ce-eccd-4d7a-b300-2f46726598ee"),   // PrettyNice
//			UUID.fromString("3e953cf0-74e7-4faa-ad92-350aa46e57af"),   // Hamutzi
			UUID.fromString("44a5a403-6223-4107-a38a-6e7f32621688"),   // HAYPERDIG
			UUID.fromString("794aae13-1f77-4702-a5ce-6008a37c9fb6"),   // FeRReTSn
//			UUID.fromString("2ff5a880-d39d-4d4f-806c-91247195ed9f"),   // FirstWin_YT
			UUID.fromString("662fa234-b866-4ade-bef4-2a48de6daae6"),   // ITSKADOSH
			UUID.fromString("6c88c6ce-1f0d-485b-a36d-5073c88e5a59"),   // SNOWFLAKE_YT
			UUID.fromString("708e256d-1399-4ede-b494-3a37e8c6369d"),    // ImFunny
			UUID.fromString("3066c0e3-1bae-4ac4-8b66-ac2a43d9b565"),    // Snowy
			UUID.fromString("e1921382-e825-4114-9c5c-e34b94dbf404")   // Bomb1LA
//			UUID.fromString("ea55e117-7447-4b8e-bb00-14ebbf0d3851")    // Tapuzimo
	};
	
	public static UUID[] spectators = new UUID[] {
			UUID.fromString("07dfc06b-ab4f-4040-b4f6-003cd5464bcb")    // 112123
	};
	
	
	public static boolean isYoutuber(UUID u) {
		for(UUID yts : Constants.youtubers) {
			if(u.toString().equalsIgnoreCase(yts.toString()))
				return true;
		}
		return false;
	}
	
	public static boolean isSpectator(UUID u) {
		for(UUID specs : Constants.spectators) {
			if(u.toString().equalsIgnoreCase(specs.toString()))
				return true;
		}
		return false;
	}
}
