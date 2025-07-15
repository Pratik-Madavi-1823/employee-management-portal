import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../http.service';
import { Employee } from '../utility/employee';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  empobj:Employee=<Employee>{};

  constructor(private route:ActivatedRoute,
              private service:HttpService,
              private router: Router
  ){}

  ngOnInit(): void {
    this.getDataFromUrl();
  }

  getDataFromUrl(){
    this.route.paramMap
    .subscribe((param)=>{
      this.service.getRecordById(param.get("id"))
      .subscribe((response:any)=>{
        this.empobj=response;
        console.log(this.empobj);
      })
      
    })
  }

  OnUpdate() {
    this.router.navigate(['/home'], {
      state: {
        empObj: this.empobj
      }
    });
  }

}
