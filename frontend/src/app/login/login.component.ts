import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {
 
  incorrect:string='';
  EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  constructor(private service:HttpService,
              private router:Router,
              private title:Title
  ) { }
 

  ngOnInit(): void {
   document.body.className="bg_background";
   this.title.setTitle("Login | ProjectWork");
  }

  onSubmit(f:NgForm){
      console.log(f);
      let obj={
        emailid:f.value.emailid,
        password:f.value.password
      }

      this.service.loginUser(obj)
      .subscribe((response:any)=>{
        console.log(response);
        if(response.msg=="Invalid User"){
          this.incorrect=response.msg;
        }else{
          // valid user 
          // navigate to home/dashboard comp
          sessionStorage.setItem("username",response.user.name)
          this.router.navigate(['/home']);
        }
      })
  }
  
  ngOnDestroy(): void {
    document.body.className='';
  }

}
