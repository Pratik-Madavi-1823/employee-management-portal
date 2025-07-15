import { Component, OnInit, TemplateRef, ViewChild, AfterViewInit } from '@angular/core';
import { HttpService } from '../http.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Employee } from '../utility/employee';
import { DialogService } from '../dialog.service';
import { ToastrService  } from 'ngx-toastr';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

empData:any[]=[];
isradidoCheck:boolean=false;
modalRef!: BsModalRef; 
empObj:Employee=<Employee>{};

@ViewChild('popUp') popUp!: TemplateRef<any>;

nameSearch:string='';
p:number=1;

isImageclick:boolean|undefined=undefined;

config = {
  animated: true,
  
  ignoreBackdropClick: true,
  class: "alert alert-danger"
};
  constructor(private service:HttpService,
    private modalservice:BsModalService,
    private dialogservice:DialogService,
    private toaster:ToastrService,
    private title:Title
  ){}

ngOnInit(): void {
  this.getDataFromBackend();
  this.title.setTitle("Dashboard | project")
}

getDataFromBackend(){
    this.service.getAllRecord()
    .subscribe((response:any)=>{
      this.empData=response;
    })
}

ngAfterViewInit(): void {
  const nav = history.state;
  if (nav.empObj) {
    this.empObj = nav.empObj;
    // Open modal after view is initialized
    setTimeout(() => {
      this.modalRef = this.modalservice.show(this.popUp);
    });
  }
}


onUpdate(popUp:TemplateRef<any>){
  if(this.radioBtnCheck()){
    // display the pop up box
    this.modalRef = this.modalservice.show(        
      popUp, this.config);
  }else{
    // alert("Please Select the Record to be Updatted")
    this.toaster.warning("Please Select the Record to be Updated")
  }
}

radioBtnCheck(){
    if(this.isradidoCheck)
      return true;
    else 
    return false;
}

onEdit(item:any){
  this.isradidoCheck=true;
  this.empObj=item;
}

onDelete(){
  if(this.radioBtnCheck()){
    // delete the Record flow
    this.dialogservice.OpenConfirmDialog('Are you sure to delete this record?')
    .afterClosed()
    .subscribe((result)=>{
      console.log(result);
        if(result){
          //delete a record
          this.service.deleteRecord(this.empObj.id)
          .subscribe((respose)=>{
            console.log(respose);
            this.getDataFromBackend();
            
          })
        }else{
          alert("Record is not deleted...")
        }
    })
    
  }else{
    // alert("Please Select the Record to be Deleted")
    this.toaster.error("Please Select the Record to be Deleted")
  }
}

onFileUpload(fileUpload:TemplateRef<any>){

  this.isImageclick=true;

  this.modalRef = this.modalservice.show(        
    fileUpload, this.config);
}


onStatusChange() {
  if (!this.radioBtnCheck()) {
    this.toaster.warning("Please select a record to update status");
    return;
  }

  if (this.empObj.status.toLowerCase() === 'active') {
    this.empObj.status = 'Inactive';
  } else {
    this.empObj.status = 'Active';
  }

  this.empObj.updateddtm = Date.now();
  this.empObj.updatedby = sessionStorage.getItem("username");

  this.service.updateRecord(this.empObj).subscribe({
    next: (res) => {
      this.toaster.success('Status updated successfully');
      this.getDataFromBackend();
    },
    error: (err) => {
      this.toaster.error('Error updating status');
      console.error(err);
    }
  });
}

}
