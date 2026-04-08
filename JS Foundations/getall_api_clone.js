const response = {
    totalElements: 3,
    totalPages: 1,
    data: [
    {
        id: 1,
        subject: 'Internet down',
        details: 'Some details',
        priority: 'HIGH',
        status: 'OPEN'
    },
    {
        id: 2,
        subject: 'Internet slow',
        details: 'Some details',
        priority: 'MEDIUM',
        status: 'CLOSED'
    },
    {
        id: 3,
        subject: 'Internet dead',
        details: 'Some details',
        priority: 'HIGH',
        status: 'OPEN'
    }
]
}
 
//destructure the response 
const {totalElements, totalPages, data} = response
const tickets = data
// console.log(tickets);

/*
tickets.forEach(ticket => {  // Problem with forEach is that it does not return anything, 
                             // hence we don't use it in react 
    
})
                             */
const dataToRender = 
tickets.map(ticket => {
    const {subject,priority,status} = ticket 
    return `${subject} -- ${priority} -- ${status}`
})

console.log(dataToRender)