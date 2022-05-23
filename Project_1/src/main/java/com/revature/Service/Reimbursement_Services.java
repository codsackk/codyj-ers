package com.revature.Service;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Roles;
import com.revature.models.Status;
import com.revature.models.Users;
import com.revature.repositories.ReimbursementDAO;

public class Reimbursement_Services {

	ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	User_Services userService = new User_Services();
	
	public void submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		
		
		
		Users employee = userService.getUserByID(reimbursementToBeSubmitted.getAuthor());
		
		if(employee.getRole() != Roles.EMPLOYEE) {
			
			throw new IllegalArgumentException("Managers cannot submit reimbursement requests!");
			
		} else { 
			
			reimbursementToBeSubmitted.setStatus(Status.PENDING);
			
			return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
//		
//		
//		Reimbursement latestReimbursement = reimbursements.get(reimbursements.size() - 1);
//		int id = latestReimbursement.getId() + 1; //
//		
//		reimbursementToBeSubmitted.setId(id);
//		reimbursementToBeSubmitted.setStatus(Status.PENDING);
//		reimbursements.add(reimbursementToBeSubmitted);
	}
	
	public void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		
		Users manager = userService.getUserById(resolverId);
		
		if(manager.getRole() != Roles.MANAGER) {
			
			throw new IllegalArgumentException("An employee cannot process reimbursement statements!");
			
		} else {
			
			unprocessedReimbursement.setResolver(resolverId);
			unprocessedReimbursement.setStatus(updatedStatus);
			
			reimbursementDAO.update(unprocessedReimbursement);
			
			return unprocessedReimbursement; 
			
		}
	}	
		
		
		
//		for (Reimbursement reimbursement : reimbursements) {
//			if (reimbursement.getId() == unprocessedReimbursement.getId()) {
//				reimbursement.setResolver(resolverId);
//				reimbursement.setStatus(updatedStatus);
//				return;
//			}
//		}
//		throw new RuntimeException("There was an error processing this reimburesment, please try again.");
	}
	
	public Reimbursement getReimbursementById(int id) {
		return reimbursementDAO.getReimbursementById(id);
//		for(Reimbursement reimbursement : reimbursements) {
//			if(reimbursement.getId() == id) {
//				return reimbursement;
//			}
//		}
//	return null;
	}
	
	public List<Reimbursement> getPendingReimbursements() {
		return reimbursementDAO.getByStatus(Status.PENDING);
		
//		List<Reimbursement> pendingReimbursements = new ArrayList<>();
//		
//		for(Reimbursement reimbursement : reimbursements) {
//			if (reimbursement.getStatus() == Status.PENDING) {
//				pendingReimbursements.add(reimbursement);
//			}
//		}
//		
//		return pendingReimbursements;
	}
	
	public List<Reimbursement> getResolvedReimbursements() {
		
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.APPROVED));
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.DENIED));
//		for(Reimbursement reimbursement : reimbursements) {
//			if (reimbursement.getStatus() == Status.APPROVED || reimbursement.getStatus() == Status.DENIED ) {
//				resolvedReimbursements.add(reimbursement);
//			}
//		}
//		
		return resolvedReimbursements;
	}
	
	public List<Reimbursement> getReimbursementByAuthor(int userId) {
		
		return reimbursementDAO.getReimbursementByUser(userId);
		
//		List<Reimbursement> userReimbursements = new ArrayList<>();
//		
//		for(Reimbursement r : reimbursements) {
//			if(r.getAuthor() == userId || r.getResolver() == userId) {
//				userReimbursements.add(r);
//			}
//		}
//	
//		return userReimbursements; 
	}	
}

