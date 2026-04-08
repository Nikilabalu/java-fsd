const user={
    id:10,
    name:'MS DHONI',
    skills:["BATTING,KEEPING,TOSS"]
}
//destructure
const{id,name,skills}=user;

//display
console.log(`Username = ${name}`);

skills.forEach(s=>console.log(s));//Arrow Function