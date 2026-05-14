import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withXsrfConfiguration } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient( withXsrfConfiguration({
      cookieName: 'XSRF-TOKEN', // Name of cookie from backend
      headerName: 'X-XSRF-TOKEN', // Header name sent to backend
    }))
],
};
