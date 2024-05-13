package be6_day12.services;

import java.util.ArrayList;
import be6_day12.dto.Menu;
import be6_day12.entities.Rank;


public class RankService extends Service<Rank> {
	
	
	public Rank getById(int point, Menu menu) {
		ArrayList<Rank>rankOptions = menu.rankOptions;
		
		for (Rank rank : rankOptions) {
			if(point >=rank.lowerThreshold && point <= rank.upperThreshold) {
				return rank;
			}
		}
		return null;
		 
		
	}

	@Override
	public Rank getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
