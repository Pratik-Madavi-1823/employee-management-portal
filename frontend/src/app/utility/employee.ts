export interface Employee{
    
    id:number;
    name:string;
    department:string;
    status:string;
    phoneno:number;
    country:{
        cid:number,
        cname:string
    }
    createddtm:number;
    createdby:string;
    updateddtm:number;
    updatedby:string|null;  
  
  }