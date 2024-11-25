import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-create-hall',
  templateUrl: './create-hall.component.html',
  styleUrl: './create-hall.component.scss'
})
export class CreateHallComponent {

  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private companyService: CompanyService
  ){}

  ngOnInit(){
    this.validateForm = this.fb.group({
      hallName: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]],
    })
  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () =>{
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  postHall(){
    const formData : FormData = new FormData();

    formData.append('img',this.selectedFile);
    formData.append('hallName',this.validateForm.get('hallName').value);
    formData.append('description',this.validateForm.get('description').value);
    formData.append('price',this.validateForm.get('price').value);

    this.companyService.postHall(formData).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        'Hall added successful',
        { nzDuration: 5000 }
      );
      this.router.navigateByUrl('/company/ads');
    }, error => {
      this.notification
      .error(
        'ERROR',
        `${error.error}`,
        { nzDuration: 5000 }
      )
    });
  }
}
