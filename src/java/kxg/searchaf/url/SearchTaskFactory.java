package kxg.searchaf.url;

public class SearchTaskFactory {

	private static SearchTask searchTask = null;

	public static SearchTask getInstance() {
		if (searchTask == null)
			searchTask = new SearchTask();
		return searchTask;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	}

}
