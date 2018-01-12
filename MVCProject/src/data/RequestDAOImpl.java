package data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Business;
import entities.Request;

@Repository
@Transactional
public class RequestDAOImpl implements RequestDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Request> index(int uid) {
		String query = "SELECT r FROM Request r WHERE r.user.id = :id"; 
		return em.createQuery(query, Request.class).setParameter("id", uid)
				.getResultList();
	}

	@Override
	public Request show(int uid, int rid) {
		return em.find(Request.class, rid); 
	}

	@Override
	public Request create(int uid, String requestJson) {
		ObjectMapper mapper = new ObjectMapper(); 
		Request request = null; 
		try {
			request = mapper.readValue(requestJson, Request.class);
			em.persist(request);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	public Request update(int uid, int rid, String requestJson) {
		ObjectMapper mapper = new ObjectMapper();
		Request request = null;
		Request orgRequest = null;
		try {
			orgRequest = em.find(Request.class, rid);
			request = mapper.readValue(requestJson, Request.class);
			orgRequest.setDescription(request.getDescription());
			orgRequest.setCompleteDate(request.getCompleteDate());
			orgRequest.setCompleted(request.isCompleted());
			orgRequest.setActive(request.isActive());
			orgRequest.setImg(request.getImg());
			orgRequest.setExpireDate(request.getExpireDate());
			orgRequest.setEstimate(request.getEstimate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgRequest;
	}

	@Override
	public Boolean destroy(int uid) {
		Request request = em.find(Request.class, uid);
				if (!request.isCompleted()) {
					request.setCompleted(true);
				}
				return true;
	}

}