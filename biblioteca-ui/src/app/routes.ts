import { Route } from '@angular/router';

export const ROUTES: Route[] = [
    {
        path: '',
        loadComponent: () => import('./modules/home/home.component').then(m => m.HomeComponent),
        children: [{ path: '', loadChildren: () => import('./modules/home/home.routes') }],
    },
    {
        path: 'pages',
        loadChildren: () => import('./layout/pages/routes'),
    },
    {
        path: '**',
        redirectTo: 'pages/not-found',
    },
] as Route[];
