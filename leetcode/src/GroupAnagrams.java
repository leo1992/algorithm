package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		List<List<String>> result = groupAnagrams(strs);
		for (List<String> group : result) {
			for (String item : group) {
				System.out.print(item + ", ");
			}
			System.out.println("");
		}
	}
	
	public static List<List<String>> groupAnagramsBackUp(String[] strs) {
		int length = strs.length;
		if (length < 1) return null;
		int[][] strCodes = new int[length][26];
		int[] group = new int[length];
		int[][] map = new int[length+1][length+1];
		group[0] = 1;
		map[group[0]][0] = 1;
		map[group[0]][1] = 1;
		for (int i = 0; i < length ; i++) {
			if (strs[i] == null) continue;
 			int itemLength = strs[i].length();
			if (itemLength == 0) continue;
			for (int j=0; j< itemLength; j++) {
				strCodes[i][strs[i].charAt(j) - 'a'] ++ ;
			}
		}		
		int maxGroup = 1;
		for (int i = 1; i < length ; i ++) {
			int findIndex = i;
			for (int j = 0; j < i; j++) {
				boolean isSimilar = true;
				for (int  k = 0; k <26; k++) {
					if (strCodes[i][k] != strCodes[j][k]) {
						isSimilar = false;
					}
				}
				if (isSimilar) {
					findIndex = j;
					break;
				}		
			}
			int groupIndex = 0;
			if (findIndex != i) {
				groupIndex = group[findIndex];
			} else {
				maxGroup ++;
				groupIndex = maxGroup;	
			}
			group[i] = groupIndex;
			map[groupIndex][0] ++ ;
			int cur = map[groupIndex][0];
			map[groupIndex][cur] = i+1;	
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 1; i<= maxGroup; i++) {
			if (map[i][0] == 0) continue;
			List<String> strings = new ArrayList<>();
			for (int j = 1; j <= map[i][0]; j++) {
				if (map[i][j] == 0) continue;
				strings.add(strs[map[i][j]-1]);
			}
			result.add(strings);
		}
		return result;      
    }	
	
	public static List<List<String>> groupAnagrams(String[] strs) {
		int [] index = {2, 3,  5,  7,  11, 13, 17, 19, 23, 29, 31, 37, 41,
				43, 47, 54, 61, 67, 71, 73, 79, 83, 89, 97, 101,103};
		int length = strs.length;
		if (length < 1) return null;;
		int[] valueArr = new int[length];
		int[] group = new int[length];
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> first = new ArrayList<String>();
		first.add(strs[0]);
		result.add(first);
		group[0] = 0;
		for (int i = 0; i < length ; i++) {
			if (strs[i] == null) continue;
 			int itemLength = strs[i].length();
			if (itemLength == 0) continue;
			int value = 1;
			for (int j=0; j< itemLength; j++) {
				value *= index[strs[i].charAt(j) - 'a'];
			}
			valueArr[i] = value;
		}		
		for (int i = 1; i < length ; i ++) {
			boolean hasFound = false;
			for (int j = 0; j < i; j++) {
				if (valueArr[i] == valueArr[j]) {
					group[i] = group[j];
					result.get(group[i]).add(strs[i]);
					hasFound = true;
					break;
				}		
			}
			if (!hasFound) {
				List<String> temp = new ArrayList<String>();
				temp.add(strs[i]);
				result.add(temp);
				group[i] = result.size()-1;
			}
		}
		return result;      
    }	

}
