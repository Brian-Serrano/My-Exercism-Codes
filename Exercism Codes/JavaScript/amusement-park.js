export function createVisitor(name, age, ticketId) {
    return { name: name, age: age, ticketId: ticketId };
  }
  
  export function revokeTicket(visitor) {
    visitor.ticketId = null;
    return visitor;
  }
  
  export function ticketStatus(tickets, ticketId) {
    if(ticketId in tickets){
      return tickets[ticketId] != null ? "sold to " + tickets[ticketId] : "not sold";
    }
    else {
      return "unknown ticket id";
    }
  }
  
  export function simpleTicketStatus(tickets, ticketId) {
    return (ticketId in tickets && tickets[ticketId] != null) ? tickets[ticketId] : "invalid ticket !!!";
  }
  
  export function gtcVersion(visitor) {
    return ("gtc" in visitor) ? visitor.gtc.version : visitor.gtc;
  }