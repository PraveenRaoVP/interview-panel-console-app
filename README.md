# Interview Panel Console Application

## Architecture & Flow Of Application
![Imgur](https://i.imgur.com/v9mQUb9.png)

### Login Screen:-
![Imgur](https://i.imgur.com/mZhgxo6.png)

### Creating Interview Panel
![Imgur](https://i.imgur.com/XSpx4EQ.png)

### Adding Candidate to Queue
![Imgur](https://i.imgur.com/T1aCnLT.png)

### Viewing Interview Panel
![Imgur](https://i.imgur.com/sFFvCgx.png)

### Viewing Panel After the Interview Of First Candidate Terminates:-
![Imgur](https://i.imgur.com/xr2G3kV.png)

### Change result of Candidate
![Imgur](https://i.imgur.com/wcDvDd5.png)

## Type of Application
B2B (Business-to-Business)

## Target Users
Administrators

## Development Time
3 days

## Tech Stack
Java

## Features visible to Admin (Phase 1)
- Login
- Add interviewer(s) and create a new interview session
- Add candidate to queue
- Remove candidate from queue
- Mark a candidate that has already been interviewed
- Remove Interviewer
- Terminate all interview sessions
- Log out

## Features to be Automated
- If there are multiple interviewers, add a new candidate to the interview panel with less number of candidates in the queue
- Assignments of ID

## Classes
### Candidate class:
- candidateId: int or UUID
- candidateName: String
- candidateDOB: Date
- positionForInterviewing: String
- interviewStatus: enum InterviewStatus
- candidateResume: blob

### InterviewStatus enum:
- WAITING_FOR_INTERVIEW
- BEING_INTERVIEWED
- UNDER_REVIEW
- SELECTED
- NOT_SELECTED

### Admin class:
- adminId: int or UUID
- adminName: String

### Interviewer class:
- employeeId: int or UUID
- employeeName: String
- employeeDesignation: String
- yearsOfExperience: int
- employeeDepartment: String

### InterviewPanel class:
- interviewPanelId: int or UUID
- interviewerDetails: Interviewer
- jobDescription: String
- candidateQueue: Queue\<Candidate\>
- createdAt: Date
- endTime: Date

## Interview Panel Options
1. Add new Interview Panel
2. Terminate Current Interview In a panel
3. Clear an Interview Panel
4. Clear all queues
5. Delete interview panel
6. View All Interview Panels

## Candidate Options
1. Add Candidate to Panel
2. Remove Candidate
3. Change result of candidate

## Admin Options
1. Add New Admin
2. Remove Admin
3. Update Admin
