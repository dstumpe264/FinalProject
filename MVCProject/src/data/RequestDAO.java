package data;

import java.util.List;

import entities.Request;

public interface RequestDAO {

	public List<Request> index(int uid);

	  public Request show(int uid, int rid);

	  public Request create(int uid, String requestJson);

	  public Request update(int uid, int rid, String requestJson);

	  public Boolean destroy(int uid);
}