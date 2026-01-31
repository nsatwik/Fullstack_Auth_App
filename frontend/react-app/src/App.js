
import React,{useState} from 'react';
function App(){
 const [msg,setMsg]=useState("");
 const login=async()=>{
  const r=await fetch("http://localhost:8080/api/auth/login",{
   method:"POST",headers:{"Content-Type":"application/json"},
   body:JSON.stringify({username:"test",password:"test"})
  });
  const d=await r.json(); setMsg(d.message);
 };
 return (<div style={{background:"#111",color:"#0f0",height:"100vh",padding:40}}>
  <h1>Welcome</h1>
  <button onClick={login}>Login</button>
  <p>{msg}</p>
 </div>);
}
export default App;
