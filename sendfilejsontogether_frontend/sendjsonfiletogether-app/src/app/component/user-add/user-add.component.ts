import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { ApiCallService } from '../../services/api-call.service';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit{

  user = new User(0, "" , "", "");
  file!:File

  constructor(private api:ApiCallService) {}

  ngOnInit(): void {
      
  }

  onChangeFileField(event:any){
    // console.log(event.target.files[0]);
    this.file=event.target.files[0];
    this.user.imageName = this.file!.name;
  }

  doRegister(){
    this.api.postUserData(this.user, this.file).subscribe({
      next:(response)=>{
        console.log(response);
        alert("Done")
      },
      error:(error)=>{
        console.log(error);
        alert("Error")
      },
      complete:()=>{
        console.log("Request Completed");
      }
    })
  }

}
