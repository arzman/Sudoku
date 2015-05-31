package sudoku.arz.application;

import java.util.ArrayList;
import java.util.HashMap;

import sudoku.arz.possiblevalues.AlreadyFoundValuesUpdator;
import sudoku.arz.possiblevalues.BlockInterActionValuesUpdator;
import sudoku.arz.possiblevalues.DisjointChainQuadValuesUpdator;
import sudoku.arz.possiblevalues.DisjointChainTripleValuesUpdator;
import sudoku.arz.possiblevalues.NakedPairValuesUpdator;
import sudoku.arz.possiblevalues.PossibleValueUpdator;
import sudoku.arz.possiblevalues.TwinsValuesUpdators;

public class PossibleValueUpdatorProvider {
	
	
	

	private static PossibleValueUpdatorProvider _instance;
	
	private HashMap<String, PossibleValueUpdator> _updatorMap;
	
	private ArrayList<String> _orderedName;
	
	
	private PossibleValueUpdatorProvider() {
		
		_updatorMap = new HashMap<String,PossibleValueUpdator>();
		
		_orderedName = new ArrayList<String>();
		
		_updatorMap.put("Disjoint Chain 4", new DisjointChainQuadValuesUpdator(false));
		_updatorMap.put("Disjoint Chain 3", new DisjointChainTripleValuesUpdator(false));
		_updatorMap.put("Naked Pair", new NakedPairValuesUpdator(false));
		_updatorMap.put("Block Inter.", new BlockInterActionValuesUpdator(false));
		_updatorMap.put("Twins Values", new TwinsValuesUpdators(false));
		_updatorMap.put("Basic", new AlreadyFoundValuesUpdator(true));
		
		_orderedName.add(0,"Basic");
		_orderedName.add(1,"Twins Values");
		_orderedName.add(2,"Block Inter.");
		_orderedName.add(3,"Naked Pair");
		_orderedName.add(4,"Disjoint Chain 3");
		_orderedName.add(5,"Disjoint Chain 4");
		

		
		
	}
	
	
	public static PossibleValueUpdatorProvider getInstance() {
		
		
		if(_instance == null){
			
			_instance = new PossibleValueUpdatorProvider();
			
		}
		
		return _instance;
	}


	public ArrayList<String> getAllName() {
		
		return _orderedName;
	}


	public void setActivatedUpdator(int state) {


		for(int i=0;i<_orderedName.size();i++){
			
			if(i<=state){
				_updatorMap.get(_orderedName.get(i)).setActive(true);
				//System.out.println(_orderedName.get(i)+" actived");
				
			}else{
				_updatorMap.get(_orderedName.get(i)).setActive(false);
			}
		}
		
	}


	public ArrayList<PossibleValueUpdator> getAllUpdators() {
		
		ArrayList<PossibleValueUpdator> res = new ArrayList<PossibleValueUpdator>();
		
		for(String name : _orderedName){
			res.add(_updatorMap.get(name));
		}
		
		return res;
	}

}
