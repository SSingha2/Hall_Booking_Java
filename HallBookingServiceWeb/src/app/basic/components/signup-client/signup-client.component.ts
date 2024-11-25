import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-client',
  templateUrl: './signup-client.component.html',
  styleUrl: './signup-client.component.scss'
})
export class SignupClientComponent {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router){}

    ngOnInit(){
      this.validateForm = this.fb.group({
        email: [null, [Validators.email, Validators.required]],
        name: [null, [Validators.required]],
        lastname: [null, [Validators.required]],
        phone: [null],
        password: [null, [Validators.required]],
        checkPassword: [null, [Validators.required]],
      },{Validators: this.matchPasswords('password','checkPassword')})
    }

    private matchPasswords(password:string, confirmPassword: string){
      return (formGroup: AbstractControl) => {
        const pass = formGroup.get(password);
        const confirmPass = formGroup.get(confirmPassword);
        
        if (confirmPass?.errors && !confirmPass.errors['passwordMismatch']) {
          // Return if another validator has already found an error
          return null;
        }
  
        if (pass?.value !== confirmPass?.value) {
          confirmPass?.setErrors({ passwordMismatch: true });
        } else {
          confirmPass?.setErrors(null);
        }
        return null;
      };
    }

    submitForm(){
      this.authService.registerClient(this.validateForm.value).subscribe(res=>{
        this.notification
        .success(
          'SUCCESS',
          'Signup successful',
          { nzDuration: 5000 }
        );
        this.router.navigateByUrl('/login')
      },error =>{
        this.notification
        .error(
          'ERROR',
          `${error.error}`,
          { nzDuration:5000 }
        )
      });
    }

    get checkPassword() {
      return this.validateForm.controls['checkPassword'];
    }
}
