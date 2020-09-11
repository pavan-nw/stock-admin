import React from 'react'
import { NavLink } from 'react-router-dom'

import './navbar.css'
import { Button } from 'primereact/button'

export const Navbar: React.FC = () => (
  <div className="layout-topbar">
    <div className="p-d-flex p-jc-between">
      <div className="p-d-flex p-ai-center">
        <div className="p-mr-2 layout-topbar-title">
          Stock Admin Application
        </div>
      </div>
      <div className="p-d-flex p-ai-center">
        <div className="p-mr-2">
          <NavLink className="link-text" to="/">
            <Button
              className="p-button-text p-button-plain link-button"
              label="Home"
            />
          </NavLink>
        </div>
        <div className="p-mr-2">
          <NavLink className="link-text" to="/counter">
            <Button
              className="p-button-text p-button-plain link-button"
              label="Counter"
            />
          </NavLink>
        </div>
        <div className="p-mr-2">
          <NavLink className="link-text" to="/">
            <Button
              className="p-button-text p-button-plain link-button"
              label="Logout"
            />
          </NavLink>
        </div>
      </div>
    </div>
  </div>
)
