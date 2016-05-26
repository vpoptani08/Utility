package com.cap.jiraimporter;

public class CsvHeader {
	
	String strTicket_ID;
	String strAssignedToGroup;
	String strSubmitter;
	String strClassification;
	String strComponent;
	String strPriority;
	String strStatus;
	String strCreationDate;
	String strModificationDate;
	String strHistory_StartDate;
	String strComment;
	String strDetails;
	String strShortDescription;
	String strLOGDiary;
	String strTicket_Typ;
	String strAtt_Attachment1;
	String strAtt_Attachment2;
	String strAtt_Attachment3;
	String strAtt_Attachment4;
	String strAtt_Attachment5;
	String strExternal;

	public CsvHeader(String strTicket_ID,
			String strAssignedToGroup,   
			String strSubmitter,         
			String strClassification,    
			String strComponent,         
			String strPriority,          
			String strStatus,            
			String strCreationDate,      
			String strModificationDate,  
			String strHistory_StartDate, 
			String strComment,           
			String strDetails,           
			String strShortDescription,  
			String strLOGDiary,          
			String strTicket_Typ,        
			String strAtt_Attachment1,   
			String strAtt_Attachment2,   
			String strAtt_Attachment3,   
			String strAtt_Attachment4,   
			String strAtt_Attachment5,   
			String strExternal           
)
	{
		this.strTicket_ID = strTicket_ID; 
		this.strAssignedToGroup = strAssignedToGroup; 
		this.strSubmitter = strSubmitter; 
		this.strClassification = strClassification; 
		this.strComponent = strComponent; 
		this.strPriority = strPriority; 
		this.strStatus = strStatus; 
		this.strCreationDate = strCreationDate; 
		this.strModificationDate = strModificationDate; 
		this.strHistory_StartDate = strHistory_StartDate; 
		this.strComment = strComment; 
		this.strDetails = strDetails; 
		this.strShortDescription = strShortDescription; 
		this.strLOGDiary = strLOGDiary; 
		this.strTicket_Typ = strTicket_Typ; 
		this.strAtt_Attachment1 = strAtt_Attachment1; 
		this.strAtt_Attachment2 = strAtt_Attachment2; 
		this.strAtt_Attachment3 = strAtt_Attachment3; 
		this.strAtt_Attachment4 = strAtt_Attachment4; 
		this.strAtt_Attachment5 = strAtt_Attachment5; 
		this.strExternal = strExternal;
	}

	public String getStrTicket_ID() {
		return strTicket_ID;
	}

	public void setStrTicket_ID(String strTicket_ID) {
		this.strTicket_ID = strTicket_ID;
	}

	public String getStrAssignedToGroup() {
		return strAssignedToGroup;
	}

	public void setStrAssignedToGroup(String strAssignedToGroup) {
		this.strAssignedToGroup = strAssignedToGroup;
	}

	public String getStrSubmitter() {
		return strSubmitter;
	}

	public void setStrSubmitter(String strSubmitter) {
		this.strSubmitter = strSubmitter;
	}

	public String getStrClassification() {
		return strClassification;
	}

	public void setStrClassification(String strClassification) {
		this.strClassification = strClassification;
	}

	public String getStrComponent() {
		return strComponent;
	}

	public void setStrComponent(String strComponent) {
		this.strComponent = strComponent;
	}

	public String getStrPriority() {
		return strPriority;
	}

	public void setStrPriority(String strPriority) {
		this.strPriority = strPriority;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public String getStrCreationDate() {
		return strCreationDate;
	}

	public void setStrCreationDate(String strCreationDate) {
		this.strCreationDate = strCreationDate;
	}

	public String getStrModificationDate() {
		return strModificationDate;
	}

	public void setStrModificationDate(String strModificationDate) {
		this.strModificationDate = strModificationDate;
	}

	public String getStrHistory_StartDate() {
		return strHistory_StartDate;
	}

	public void setStrHistory_StartDate(String strHistory_StartDate) {
		this.strHistory_StartDate = strHistory_StartDate;
	}

	public String getStrComment() {
		return strComment;
	}

	public void setStrComment(String strComment) {
		this.strComment = strComment;
	}

	public String getStrDetails() {
		return strDetails;
	}

	public void setStrDetails(String strDetails) {
		this.strDetails = strDetails;
	}

	public String getStrShortDescription() {
		return strShortDescription;
	}

	public void setStrShortDescription(String strShortDescription) {
		this.strShortDescription = strShortDescription;
	}

	public String getStrLOGDiary() {
		return strLOGDiary;
	}

	public void setStrLOGDiary(String strLOGDiary) {
		this.strLOGDiary = strLOGDiary;
	}

	public String getStrTicket_Typ() {
		return strTicket_Typ;
	}

	public void setStrTicket_Typ(String strTicket_Typ) {
		this.strTicket_Typ = strTicket_Typ;
	}

	public String getStrAtt_Attachment1() {
		return strAtt_Attachment1;
	}

	public void setStrAtt_Attachment1(String strAtt_Attachment1) {
		this.strAtt_Attachment1 = strAtt_Attachment1;
	}

	public String getStrAtt_Attachment2() {
		return strAtt_Attachment2;
	}

	public void setStrAtt_Attachment2(String strAtt_Attachment2) {
		this.strAtt_Attachment2 = strAtt_Attachment2;
	}

	public String getStrAtt_Attachment3() {
		return strAtt_Attachment3;
	}

	public void setStrAtt_Attachment3(String strAtt_Attachment3) {
		this.strAtt_Attachment3 = strAtt_Attachment3;
	}

	public String getStrAtt_Attachment4() {
		return strAtt_Attachment4;
	}

	public void setStrAtt_Attachment4(String strAtt_Attachment4) {
		this.strAtt_Attachment4 = strAtt_Attachment4;
	}

	public String getStrAtt_Attachment5() {
		return strAtt_Attachment5;
	}

	public void setStrAtt_Attachment5(String strAtt_Attachment5) {
		this.strAtt_Attachment5 = strAtt_Attachment5;
	}

	public String getStrExternal() {
		return strExternal;
	}

	public void setStrExternal(String strExternal) {
		this.strExternal = strExternal;
	}
	 @Override
	public String toString() {
		return "CvsHeader [strTicket_ID=" + strTicket_ID
		+ ",strAssignedToGroup=" + strAssignedToGroup 
		+ ",strSubmitter=" + strSubmitter 
		+ ",strClassification=" + strClassification 
		+ ",strComponent=" + strComponent 
		+ ",strPriority=" + strPriority 
		+ ",strStatus=" + strStatus 
		+ ",strCreationDate=" + strCreationDate 
		+ ",strModificationDate=" + strModificationDate 
		+ ",strHistory_StartDate=" + strHistory_StartDate 
		+ ",strComment=" + strComment 
		+ ",strDetails=" + strDetails 
		+ ",strShortDescription=" + strShortDescription 
		+ ",strLOGDiary=" + strLOGDiary 
		+ ",strTicket_Typ=" + strTicket_Typ 
		+ ",strAtt_Attachment1=" + strAtt_Attachment1 
		+ ",strAtt_Attachment2=" + strAtt_Attachment2 
		+ ",strAtt_Attachment3=" + strAtt_Attachment3 
		+ ",strAtt_Attachment4=" + strAtt_Attachment4 
		+ ",strAtt_Attachment5=" + strAtt_Attachment5 
		+ ",strExternal=" + strExternal+"]";
	
}}
