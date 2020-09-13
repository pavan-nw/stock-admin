import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { actionTypes, selectors } from '../../features/counter'
import { Button } from 'primereact/button'
import { Card } from 'primereact/card'
import './counter.css'

const Counter: React.FC = () => {
  const count = useSelector(selectors.getCountValue)
  const dispatch = useDispatch()
  const footer = (
    <div>
      <Button
        className="p-button-raised p-mr-2 p-mb-2"
        onClick={() => dispatch({ type: actionTypes.DECREMENT_COUNTER })}
        label="Decrement"
      />
      <Button
        className="p-button-raised p-mb-2"
        onClick={() => dispatch({ type: actionTypes.INCREMENT_COUNTER })}
        label="Increment"
      />
    </div>
  )
  return (
    <div className="p-col-12">
      <div className="p-col-6 p-offset-3">
        <Card title="Counter" footer={footer}>
          <h4>
            Counter: <strong>{count}</strong>
          </h4>
          <p>
            Here you can increment and decrement counter value using buttons
            below. All the state updates are performed via redux actions.
          </p>
        </Card>
      </div>
    </div>
  )
}

export default Counter
